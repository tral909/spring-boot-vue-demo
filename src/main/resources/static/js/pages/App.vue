<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>Sarafan</v-toolbar-title>
            <v-spacer></v-spacer>
            <span v-if="profile">{{ profile.name }}</span>
            <v-btn icon v-if="profile" href="/logout" @click="wsDisconnect">
                <v-icon>mdi-exit-to-app</v-icon>
            </v-btn>
        </v-app-bar>
        <v-content>
            <v-container v-if="!profile">
                Необходимо авторизоваться через
                <a href="/login">Google</a>
            </v-container>
            <v-container v-if="profile">
                <message-block :messageList="messages"/>
            </v-container>
        </v-content>
    </v-app>
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