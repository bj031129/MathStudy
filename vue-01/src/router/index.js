import { createRouter, createWebHistory } from 'vue-router'
import Layout from "@/views/Layout.vue";
import Login from "@/views/Login.vue";
import Register from "@/views/Register.vue";
import InitUser from "@/views/InitUser.vue";
import Question from "@/views/layout/Question.vue";
import Setting from "@/views/layout/Setting.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  // history: createWebHistory(),
  routes: [
    {
      path: '/',
      redirect: '/login',
    },
    {
      path: '/login',
      component: Login,
    },
    {
      path: '/register',
      component: Register,
    },
    {
      path: '/init-user',
      component: InitUser,
    },
    {
      path: '/layout',
      component: Layout,
      children: [
        {
          path: 'question',
          component: Question,
        },
        {
          path: 'setting',
          component: Setting,
        }
      ]
    },

  ]
})

export default router
