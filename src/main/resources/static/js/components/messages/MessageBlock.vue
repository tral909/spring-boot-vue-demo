<template>
    <v-layout align-space-around justify-start column>
        <message-input :messageList="messageList" :messageAttr="message"/>
        <message-item v-for="msg in sortedMessages"
                      style="margin-top: 10px;"
                      :msg="msg"
                      :messages="messageList"
                      :deleteMessage="deleteMessage"
                      :editMessage="editMessage"/>
        </message-item>
    </v-layout>
</template>

<script>
    import MessageItem from 'components/messages/MessageItem.vue'
    import MessageInput from 'components/messages/MessageInput.vue'

    export default {
        props: ['messageList'],
        components: {
            MessageItem, MessageInput
        },
        data() {
            return {
                message: null
            }
        },
        computed: {
            sortedMessages() {
                return this.messageList.sort((a, b) => -(a.id - b.id))
            }
        },
        methods: {
            editMessage(message) {
                this.message = message
            },
            deleteMessage(message) {
                this.$resource('http://localhost:9000/message{/id}').remove({id: message.id})
                    .then(result => {
                        if (result.ok) {
                            this.messageList.splice(this.messageList.indexOf(message), 1)
                        }
                    })
            }
        }
    }

</script>

<style>

</style>