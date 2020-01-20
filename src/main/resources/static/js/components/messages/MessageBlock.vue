<template>
    <div style="position: relative; width: 340px;">
        <message-input :messageList="messageList" :messageAttr="message"/>
        <div v-for="msg in messageList">
            <message-item
                    style="margin-top: 10px;"
                    :msg="msg"
                    :messages="messageList"
                    :deleteMessage="deleteMessage"
                    :editMessage="editMessage" />
        </div>
    </div>
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