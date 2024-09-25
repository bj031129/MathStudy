<script setup>
import { RouterLink, RouterView } from 'vue-router';

</script>

<template>
  <div class="app-container">
    <nav class="app-nav">
      <RouterLink to="/layout/question" class="nav-link">生成题目</RouterLink>
      <RouterLink to="/layout/setting" class="nav-link">修改密码</RouterLink>
      <RouterLink to="/login" class="nav-link">退出登录</RouterLink>
    </nav>
    <router-view ></router-view>
  </div>
</template>

<style scoped>
.app-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 100vh;
  background-color: #f0f2f5;
}

.app-nav {
  display: flex;
  justify-content: space-around;
  width: 100%;
  padding: 1rem;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.nav-link {
  text-decoration: none;
  color: #303133;
  font-size: 18px;
  transition: color 0.3s ease;
}

.nav-link:hover {
  color: #409eff;
}

.app-main {
  width: 100%;
  max-width: 800px;
  padding: 2rem;
  box-sizing: border-box;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
</style>