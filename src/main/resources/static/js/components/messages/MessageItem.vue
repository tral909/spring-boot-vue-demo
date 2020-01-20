<template>
    <div>
        <i>({{ msg.id }})</i> {{ msg.text }}
        <span style="position: absolute; right: 0;">
            <input type="button" @click="edit" value="Редактировать"/>
            <input type="button" @click="del" value="X"/>
        </span>
    </div>
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