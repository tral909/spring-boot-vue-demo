<template>
    <v-layout align-space-around justify-start column>
        <message-input :messageList="messageList" :messageAttr="message"/>
        <message-item v-for="msg in sortedMessages"
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
    import MessagesApi from 'api/messages'

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
                MessagesApi.remove(message.id)
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