<template>
    <v-container>
        <v-layout align-space-around justify-start column>
            <message-input :messageAttr="message"/>
            <message-item v-for="msg in sortedMessages"
                          :msg="msg"
                          :editMessage="editMessage"/>
            </message-item>
        </v-layout>
    </v-container>
</template>

<script>
    import { mapGetters } from 'vuex';
    import MessageItem from 'components/messages/MessageItem.vue'
    import MessageInput from 'components/messages/MessageInput.vue'

    export default {
        components: {
            MessageItem, MessageInput
        },
        data() {
            return {
                message: null
            }
        },
        computed: mapGetters(['sortedMessages']),
        methods: {
            editMessage(message) {
                this.message = message
            }
        }
    }


</script>

<style>

</style>