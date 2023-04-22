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
      },
      {
        path:'/Book1',
        name:'Book1',
        component:()=>import('../views/Books/Book1.vue')
      },
      {
        path:'/Book2',
        name:'Book2',
        component:()=>import('../views/Books/Book2.vue')
      },
      {
        path:'/Book3',
        name:'Book3',
        component:()=>import('../views/Books/Book3.vue')
      },
      {
        path:'/Book4',
        name:'Book4',
        component:()=>import('../views/Books/Book4.vue')
      },
    ]
  }

]
//创建路由对象
const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
