<template>
    <v-card>
        <v-card-text primary-title>
            <i>({{ msg.id }})</i> {{ msg.text }}
        </v-card-text>
        <v-card-actions>
            <v-btn @click="edit" small text rounded>Редактировать</v-btn>
            <v-btn icon @click="del" value="X">
                <v-icon>mdi-delete</v-icon>
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    import { mapActions } from 'vuex'
    export default {
        props: ['msg', 'editMessage'],
        methods: {
            ...mapActions(['removeMessageAction']),
            edit() {
                this.editMessage(this.msg)
            },
            del() {
                this.removeMessageAction(this.msg)
            }
        }
    }

</script>

<style>

</style>