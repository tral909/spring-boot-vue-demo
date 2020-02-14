import Vue from 'vue'
import VueRouter from 'vue-router'
import MessageBlock from 'pages/MessageBlock.vue'
import Auth from 'pages/Auth.vue'
import Profile from 'pages/Profile.vue'


Vue.use(VueRouter)

const routes = [
    { path: '/', component: MessageBlock },
    { path: '/auth', component: Auth },
    { path: '/profile', component: Profile },
    { path: '/*', component: MessageBlock },
]

export default new VueRouter({
    mode: 'history',
    routes
})