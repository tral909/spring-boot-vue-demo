var messageApi = Vue.resource('http://localhost:9000/message{/id}');

Vue.component('message-input', {
    props: ['messageList', 'messageAttr'],
    template: '<div style="margin-left: 10px;">' +
        '<input type="text" v-model="text" style="margin-right: 10px;" placeholder="Текст сообщения"/>' +
        '<input type="button" @click="save" value="Сохранить"/>' +
        '</div>',
    data() {
        return {
            text: '',
            id: ''
        }
    },
    // watch функции следят за изменениями переменных props и data и вызываются при их модификации
    watch: {
        messageAttr(newVal, oldVal) {
            this.text = newVal.text;
            this.id = newVal.id
        }
    },
    methods: {
        save() {
            if (this.text.trim() == '') return; // TODO заменить на валидацию

            let message = { text: this.text }
            if (this.id) {
                messageApi.update({id: this.id}, message)
                    .then(result => result.json()
                        .then(data => {
                            let index = getIndex(this.messageList, data.id)
                            this.messageList.splice(index, 1, data)
                        }))
            } else {
                messageApi.save(message)
                    .then(result => result.json()
                        .then(data => this.messageList.push(data)))
            }
            this.id = ''
            this.text = ''
        }
    }
})

function getIndex(list, id) {
    for (let i = 0; i < list.length; i++) {
        if (list[i].id === id) return i;
    }
    return -1;
}

Vue.component('message-block', {
    props: ['messageList'],
    template:
        '<div style="position: relative; width: 340px;">' +
        '<message-input :messageList="messageList" :messageAttr="message"/>' +
        '<div v-for="msg in messageList"><message-item style="margin: 10px;" :msg="msg" :messages="messageList" :editMethod="editMessage"/></div>' +
        '</div>',
    data() {
        return {
            message: null
        }
    },
    methods: {
        editMessage(message) {
            this.message = message
        }
    }
})

Vue.component('message-item', {
    props: ['msg', 'editMethod', 'messages'],
    template:
        '<div>' +
        '<i>({{ msg.id }})</i> {{ msg.text }}' +
        '<span style="position: absolute; right: 0;">' +
        '<input type="button" @click="edit" value="Редактировать"/>' +
        '<input type="button" @click="del" value="X"/>' +
        '</span>' +
        '</div>',
    methods: {
        edit() {
            this.editMethod(this.msg)
        },
        del() {
            messageApi.remove({id: this.msg.id})
                .then(result => {
                    if (result.ok) {
                        this.messages.splice(this.messages.indexOf(this.msg), 1)
                    }
                })
        }
    }
});

var app = new Vue({
    el: '#app',
    template:
        '<div>' +
        '<div v-if="!profile">Необходимо авторизоваться через <a href="/login">Google</a></div>' +
        '<div v-else>' +
        '<div>{{ profile.name }}&nbsp;<a href="/logout">Выйти</a></div>' +
        '<message-block :messageList="messages"/>' +
        '</div>' +
        '</div>',
    data() {
        return {
            messages: frontendData.messages,
            profile: frontendData.profile
        }
    },
    created: function () {
//        messageApi.get()
//           .then(result => result.json()
//               .then(data => data.forEach(msg => this.messages.push(msg))))
    }
})