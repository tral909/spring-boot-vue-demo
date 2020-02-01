import Vue from 'vue'

const messages = Vue.resource('http://localhost:9000/message{/id}')

export default {
    add: message => messages.save({}, message),
    update: message => messages.update({id: message.id}, message),
    remove: id => messages.remove({id}),
}