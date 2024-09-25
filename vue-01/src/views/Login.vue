<template>
  <div class="login-container">
    <el-card class="login-card">
      <template #header>
        <h2 class="login-title">登录您的账户</h2>
      </template>

      <template>
        <el-dialog :visible.sync="dialogVisible" title="错误" @close="dialogVisible = false">
          <span>{{ dialogMessage }}</span>
          <template #footer>
            <el-button @click="dialogVisible = false">关闭</el-button>
          </template>
        </el-dialog>
      </template>


      <el-form :model="formData" :rules="rules" ref="loginForm" @submit.prevent="handleSubmit">
        <el-form-item prop="username">
          <el-input v-model="formData.username" placeholder="用户名">
            <template #prefix>
              <el-icon><User /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="formData.password" type="password" placeholder="密码" show-password>
            <template #prefix>
              <el-icon><Lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" native-type="submit" :loading="isLoading" class="login-button">
            {{ isLoading ? '登录中...' : '登录' }}
          </el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="text" @click="goToRegister" class="register-button">
            注册
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { ElMessage, ElForm } from 'element-plus'
import { User, Lock } from '@element-plus/icons-vue'
import router from '@/router/index.js'
import { useUserInfo } from "@/stores/info.js";
import { testLogin } from '@/apis/api.js'
import { onMounted } from 'vue';

const dialogVisible = ref(false);
const dialogMessage = ref('');



onMounted(() => {
  userInfo.email = ''
  userInfo.password = ''
  userInfo.username = ''
})

const userInfo = useUserInfo()
const loginForm = ref(null)
const isLoading = ref(false)

const formData = reactive({
  username: '',
  password: ''
})

const rules = {
  username: [
    {required: true, message: '请输入用户名', trigger: 'blur'},
    {min: 6, max: 30, message: '用户名长度应在6到30个字符之间', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
    {min: 6, max: 30, message: '密码长度应在6到30个字符之间', trigger: 'blur'}
  ]
}
const handleSubmit = async () => {
  try {
    // 验证表单
    // await loginForm.value.validate();

    // 设置加载状态
    isLoading.value = true;

    // 调用登录接口
    const loginResult = await testLogin(formData.username, formData.password);

    // 判断登录结果
    if (loginResult.status == '1') {
      // 保存用户信息
      userInfo.username = formData.username;
      userInfo.password = formData.password;

      console.log('登录成功!');
      ElMessage.success('登录成功');

      // 跳转到目标页面
      goToLayout()
    } else {
      console.error('登录失败: 用户名或密码错误');
      ElMessage.error('登录失败: 用户名或密码错误');
      alert('密码或用户名错误，请重试');
    }
  } catch (error) {
    console.error('登录过程中出现错误:', error);
    ElMessage.error('登录失败，请检查您的凭证');

    alert('密码或用户名错误，请重试');
  } finally {
    // 无论成功还是失败，都需要重置加载状态
    isLoading.value = false;
  }
};
const goToLayout = () => {
  router.push('/layout/question')
}
const goToRegister = () => {
  router.push('/register')
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5;
}

.login-card {
  width: 100%;
  max-width: 400px;
}

.login-title {
  text-align: center;
  font-size: 24px;
  color: #303133;
}

.login-button {
  width: 100%;
}

.register-button {
  text-align: center;
  color: #409eff;
}
</style>