import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';
import AddBook from '@/views/staff/AddBook.vue';
import Login from '@/views/Login.vue'
import staffMenu from '@/views/staff/components/staffMenu.vue'
import ManageBook from '@/views/staff/ManageBook.vue'
import SearchBook from '@/views/staff/SearchBook.vue'
const routes: Array<RouteRecordRaw> = [
 {
  path: "/",
  name: "login",
  component: Login
 },{
  path: "/staff",
  component: staffMenu,
  children: [
    {
      path: "/addBook",
      name: "addBook",
      component: AddBook
    },{
      path: "/manageBook",
      name: "manageBook",
      component: ManageBook
    },{
      path: "/searchBook",
      name: "searchBook",
      component: SearchBook
    }

  ]
 }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
