import Vue from 'vue'
import Router from 'vue-router'
import ProductList from '../components/ProductList.vue'
import ShoppingCart from '../components/ShoppingCart.vue'
import UserLogin from '../components/UserLogin.vue'
import UserRegister from '../components/UserRegister.vue'
import OrderList from '../components/OrderList.vue'
import BookRecommend from '../components/BookRecommend.vue'

Vue.use(Router)

export default new Router({
    routes: [
        { path: '/login', component: UserLogin },
        { path: '/', redirect: '/login' }, // 默认访问跳转到 /login
        { path: '/ProductList', component: ProductList },
        { path: '/ShoppingCart', component: ShoppingCart },
        { path: '/UserRegister', component: UserRegister },
        { path: '/orders', component: OrderList },
        { path: '/BookRecommend', component: BookRecommend }
    ]
})