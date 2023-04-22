import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';
import PersonInfo from '@/views/staff/PersonInfo.vue';
import Login from '@/views/Login.vue'
import staffMenu from '@/views/staff/components/staffMenu.vue'
import ManageBook from '@/views/staff/ManageBook.vue'
import SearchBook from '@/views/staff/SearchBook.vue'
import AddBook from '@/views/staff/AddBook.vue';
const routes: Array<RouteRecordRaw> = [

 {
  path: "/",
  name: "login",
  component: Login
 },

 {
  path: "/staff",
  component: staffMenu,
  children: 
  [
    {
      path: "/personInfo",
      name: "personInfo",
      component: PersonInfo
    },
    {
      path: "/manageBook",
      name: "manageBook",
      component: ManageBook
    },
    {
      path: "/searchBook",
      name: "searchBook",
      component: SearchBook
    },
    {
      path: "/addBook",
      name: "addBook",
      component: AddBook
    }

  ]
 }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
