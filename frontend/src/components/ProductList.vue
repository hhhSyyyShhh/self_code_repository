<template>
  <el-container style="height: 100vh">
    <!-- 侧边栏 -->
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1', '2', '3', '4']">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-goods"></i>商品管理</template>
          <el-menu-item index="1-1" @click="view = 'product'">商品列表</el-menu-item>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-shopping-cart-1"></i>购物车管理</template>
          <el-menu-item index="2-1" @click="view = 'ShoppingCart'">购物车</el-menu-item>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-document"></i>订单管理</template>
          <el-menu-item index="3-1" @click="view = 'order'">我的订单</el-menu-item>
        </el-submenu>
        <el-submenu index="4">
          <template slot="title"><i class="el-icon-star-off"></i>推荐管理</template>
          <el-menu-item index="4-1" @click="view = 'recommend'">热销推荐</el-menu-item>
        </el-submenu>
      </el-menu>
    </el-aside>

    <!-- 主体内容 -->
    <el-container>
      <el-header style="background-color: #B3C0D1; text-align: right; padding-right: 20px; cursor: pointer;"
        @click="$router.replace('/login')">
        <span>当前用户：王小虎（点击退出）</span>
      </el-header>

      <el-main>
        <!-- 商品列表 -->
        <div v-if="view === 'product'">
          <h2>商品列表</h2>
          <el-table :data="products" style="width: 100%">
            <el-table-column prop="id" label="商品编号" />
            <el-table-column prop="name" label="商品名" />
            <el-table-column prop="price" label="价格" />
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button type="primary" @click="addToCart(scope.row)">加入购物车</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>

        <!-- 购物车 -->
        <div v-if="view === 'ShoppingCart'">
          <h2>购物车</h2>
          <el-table :data="ShoppingCart" style="width: 100%">
            <el-table-column prop="id" label="商品编号" />
            <el-table-column prop="name" label="商品名" />
            <el-table-column prop="price" label="价格" />
          </el-table>
          <el-button type="success" @click="submitOrder" style="margin-top: 20px;">提交订单</el-button>
          <el-button type="danger" @click="removeFromCart" style="margin-top: 20px;">清空购物车</el-button>
        </div>

        <!-- 我的订单 -->
        <div v-if="view === 'order'">
          <h2>我的订单</h2>
          <el-table :data="orders" style="width: 100%">
            <el-table-column prop="orderId" label="订单号" />
            <el-table-column prop="total" label="总价" />
          </el-table>
        </div>

        <!-- 热销推荐 -->
        <div v-if="view === 'recommend'">
          <h2>热销推荐</h2>
          <el-table :data="recommendList" style="width: 100%">
            <el-table-column prop="name" label="商品名" />
            <el-table-column prop="count" label="购买次数" />
          </el-table>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      view: 'product',
      products: [],
      ShoppingCart: [],
      orders: [],
      recommendList: []
    }
  },
  mounted() {
    this.fetchProducts();
    this.getShoppingCart();
    this.getRecommendations();
  },
  methods: {
    fetchProducts() {
      this.$axios.get('/api/products').then(res => {
        this.products = res.data.map(p => ({
          id: p["products.id"],
          name: p["products.name"],
          price: p["products.price"]
        }));
      });
    },
    getShoppingCart() {
      this.$axios.get('/api/ShoppingCart/get').then(res => {
        this.ShoppingCart = res.data.map(p => ({
          id: p["shopping_cart.id"],
          name: p["shopping_cart.name"],
          price: p["shopping_cart.price"]
        }));
      });
    },
    addToCart(product) {
      this.$axios.post('/api/ShoppingCart/add', product).then(() => {
        this.$message.success('已加入购物车');
      });
    },
    removeFromCart() {
      this.$axios.delete('/api/ShoppingCart/delete').then(() => {
        this.$message.success('购物车已清空');
        this.getShoppingCart();
      });
    },
    submitOrder() {
      const order = {
        orderId: Date.now(),
        total: this.ShoppingCart.reduce((sum, item) => sum + item.price, 0)
      };
      this.orders.push(order);
      this.ShoppingCart = [];
      this.$message.success('下单成功');
    },
    getRecommendations() {
      this.$axios.get('/api/recommend').then(res => {
        this.recommendList = res.data.map(p => ({
          name: p.name,
          count: p.count
        }));
      });
    }
  }
}
</script>

<style>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>
