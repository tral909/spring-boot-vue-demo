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
    import { sendMessage } from 'util/ws'

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

                sendMessage({id: this.id, text: this.text})
                this.id = ''
                this.text = ''

                /*const message = { text: this.text }
                if (this.id) {
                    this.$resource('http://localhost:9000/message{/id}').update({id: this.id}, message)
                        .then(result => result.json()
                            .then(data => {
                                const index = getIndex(this.messageList, data.id)
                                this.messageList.splice(index, 1, data)
                            }))
                } else {
                    this.$resource('http://localhost:9000/message{/id}').save(message)
                        .then(result => result.json()
                            .then(data => this.messageList.push(data)))
                }
                this.id = ''
                this.text = ''*/
            }
        }
    }
</script>

<style>
    
</style>