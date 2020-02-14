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
    import { mapActions } from 'vuex'

    export default {
        props: ['messageAttr'],
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
            ...mapActions(['addMessageAction', 'updateMessageAction']),
            save() {
                if (this.text.trim() == '') return // TODO заменить на валидацию

                const message = { id: this.id, text: this.text }
                if (this.id) {
                    this.updateMessageAction(message)
                } else {
                    this.addMessageAction(message)
                }
                this.id = ''
                this.text = ''
            }
        }
    }
</script>

<style>
    
</style>