<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>Sarafan</v-toolbar-title>
            <v-btn text rounded v-if="profile" @click="showMessages" :disabled="$route.path === '/'">
                Сообщения
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn text rounded v-if="profile" @click="showProfile" :disabled="$route.path === '/profile'">
                {{ profile.name }}
            </v-btn>
            <v-btn icon v-if="profile" href="/logout" @click="wsDisconnect">
                <v-icon>mdi-exit-to-app</v-icon>
            </v-btn>
        </v-app-bar>
        <v-content>
            <router-view></router-view>
        </v-content>
    </v-app>
</template>

<script>
    import { mapState, mapMutations } from 'vuex'
    import { addHandler, disconnect } from 'util/ws'

    export default {
        computed: mapState(['profile']),
        methods: {
            ...mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),
            showMessages() {
                this.$router.push('/')
            },
            showProfile() {
                this.$router.push('/profile')
            },
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
        },
       beforeMount() {
            if (!this.profile) {
                this.$router.replace('/auth')
            }
        }
    }
</script>

<style>

</style>