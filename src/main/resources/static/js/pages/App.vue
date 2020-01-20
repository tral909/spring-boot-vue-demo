<template>
    <div>
        <div v-if="!profile">Необходимо авторизоваться через <a href="/login">Google</a></div>
        <div v-else>
            <div>{{ profile.name }}&nbsp;<a href="/logout">Выйти</a></div>
            <message-block :messageList="messages"/>
        </div>
    </div>
</template>

<script>
    import MessageBlock from 'components/messages/MessageBlock.vue'

    export default {
        components: {
            MessageBlock
        },
        data() {
            return {
                messages: frontendData.messages,
                profile: frontendData.profile
            }
        }
    }
</script>

<style>

</style>