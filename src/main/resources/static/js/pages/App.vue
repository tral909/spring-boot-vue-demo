<template>
    <div>
        <div v-if="!profile">Необходимо авторизоваться через <a href="/login">Google</a></div>
        <div v-else>
            <div>{{ profile.name }}&nbsp;<a href="/logout" @click="wsDisconnect">Выйти</a></div>
            <message-block :messageList="messages"/>
        </div>
    </div>
</template>

<script>
    import MessageBlock from 'components/messages/MessageBlock.vue'
    import { addHandler, disconnect } from 'util/ws'
    import { getIndex } from 'util/collections'

    export default {
        components: {
            MessageBlock
        },
        data() {
            return {
                messages: frontendData.messages,
                profile: frontendData.profile
            }
        },
        created() {
            addHandler(data => {
                let index = getIndex(this.messages, data.id)
                if (index > -1) {
                    this.messages.splice(index, 1, data)
                } else {
                    this.messages.push(data)
                }
            })
        },
        methods: {
            wsDisconnect() {
                disconnect()
            }
        }
    }
</script>

<style>

</style>