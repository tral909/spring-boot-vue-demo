var messageApi = Vue.resource('/message{/id}');

Vue.component('item-list', {
        props: ['messageList'],
        template:
            '<div>' +
            '<div v-for="msg in messageList"><i>({{ msg.id }})</i> {{ msg.text }}</div>' +
            '</div>',
        created: function () {
            messageApi.get()
                .then(result => result.json()
                .then(data => data.forEach(msg => this.messageList.push(msg))))
        }
    }
);

var app = new Vue({
    el: '#app',
    template: '<item-list :messageList="messages"/>',
    data: {
       messages: []
    }
})