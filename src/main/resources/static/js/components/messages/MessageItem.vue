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
    export default {
        props: ['msg', 'editMessage', 'deleteMessage', 'messages'],
        methods: {
            edit() {
                this.editMessage(this.msg)
            },
            del() {
                this.deleteMessage(this.msg)
            }
        }
    }

</script>

<style>

</style>