import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
//定义路由信息
const routes: Array<RouteRecordRaw> = [
  //布局页
  {
    path:'/',
    name:'Layout',
    component:()=>import('../views/Layout.vue'),
    children:[
      {
        path:'/PersonalSpace',
        name:'PersonalSpace',
        component:()=>import('../views/PersonalSpace.vue')
      },
      {
        path:'/Home',
        name:'Home',
        component:()=>import('../views/Home.vue')
      },
      {
        path:'/BorrowHistory',
        name:'BorrowHistory',
        component:()=>import('../views/BorrowHistory.vue')
      },
      {
        path:'/MyAppointment',
        name:'MyAppointmente',
        component:()=>import('../views/MyAppointment.vue')
      },
      {
        path:'/BookReturn',
        name:'BookReturn',
        component:()=>import('../views/BookReturn.vue')
      }
    ]
  }

]
//创建路由对象
const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
