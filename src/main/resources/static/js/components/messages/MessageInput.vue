<template>
    <div style="margin-top: 10px;">
        <input type="text" v-model="text" style="margin-right: 10px;" placeholder="Текст сообщения"/>
        <input type="button" @click="save" value="Сохранить"/>
    </div>
</template>

<script>
    function getIndex(list, id) {
        for (let i = 0; i < list.length; i++) {
            if (list[i].id === id) {
                return i
            }
        }
        return -1
    }

    export default {
        props: ['messageList', 'messageAttr'],
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

                const message = { text: this.text }
                if (this.id) {
                    this.$resource('http://localhost:9000/message{/id}').update({id: this.id}, message)
                        .then(result => result.json()
                            .then(data => {
                                const index = getIndex(this.messageList, data.id)
                                this.messageList.splice(index, 1, data)
                            }))
                } else {
                    this.$resource('http://localhost:9000/message{/id}').save(message)
                        .then(result => result.json()
                            .then(data => this.messageList.push(data)))
                }
                this.id = ''
                this.text = ''
            }
        }
    }
</script>

<style>
    
</style>