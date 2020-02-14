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
                <message-block />
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import { mapState, mapMutations } from 'vuex'
    import MessageBlock from 'components/messages/MessageBlock.vue'
    import { addHandler, disconnect } from 'util/ws'

    export default {
        components: {
            MessageBlock
        },
        computed: mapState(['profile']),
        methods: {
            ...mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),
            wsDisconnect() {
                disconnect()
            }
        },
        created() {
            addHandler(data => {
            if (data.objectType === 'MESSAGE') {
                switch(data.eventType) {
                    case 'CREATE':
                        this.addMessageMutation(data.body)
                        break
                    case 'UPDATE':
                        this.updateMessageMutation(data.body)
                        break
                    case 'REMOVE':
                        this.removeMessageMutation(data.body)
                        break
                    default:
                        console.log(`Looks like the event type is unknown ${data.eventType}`)
                }
            } else {
                console.log(`Looks like the object type is unknown ${data.objectType}`)
            }
            })
        }
    }
</script>

<style>

</style>