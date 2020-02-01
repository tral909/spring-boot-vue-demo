<template>
    <v-layout row class="ma-1">
        <v-text-field
            label="Новое сообщение"
            placeholder="Текст сообщения"
            v-model="text" />
        <v-btn @click="save">Сохранить</v-btn>
    </v-layout>
</template>

<script>
    import messagesApi from 'api/messages'

    export default {
        props: ['messageList', 'messageAttr'],
        data() {
            return {
                text: '',
                id: ''
            }
        },
        // watch функции следят за изменениями переменных props и data и вызываются при их модификации
        watch: {
            messageAttr(newVal, oldVal) {
                this.text = newVal.text;
                this.id = newVal.id
            }
        },
        methods: {
            save() {
                if (this.text.trim() == '') return // TODO заменить на валидацию
                const message = { id: this.id, text: this.text }
                if (this.id) {
                    messagesApi.update(message)
                        .then(result => result.json()
                            .then(data => {
                                const index = this.messageList.findIndex(item => item.id === data.id)
                                this.messageList.splice(index, 1, data)
                            }))
                } else {
                    messagesApi.add(message)
                        .then(result => result.json()
                            .then(data => {
                                const index = this.messageList.findIndex(item => item.id === data.id)
                                if (index > -1) {
                                    this.messageList.splice(index, 1, data)
                                } else {
                                    this.messageList.push(data)
                                }
                            })
                        )
                }
                this.id = ''
                this.text = ''
            }
        }
    }
</script>

<style>
    
</style>