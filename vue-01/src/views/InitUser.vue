<template>
  <div class="login-container">
    <el-card class="login-card">
      <template #header>
        <h2 class="login-title">注册您的账户</h2>
      </template>
      <el-form :model="formData" :rules="rules" ref="registerForm" >
        <el-form-item prop="username">
          <el-input v-model="formData.username" placeholder="用户名" >
            <template #prefix>
              <el-icon><User /></el-icon>
            </template>
          </el-input>
          <div v-if="usernameTaken" class="username-taken-message">用户名已被使用</div>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="formData.password" type="password" placeholder="密码" show-password>
            <template #prefix>
              <el-icon><Lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="confirmPassword">
          <el-input v-model="formData.confirmPassword" type="password" placeholder="确认密码" show-password>
            <template #prefix>
              <el-icon><Lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSubmit" :loading="isLoading" class="login-button">
            {{ isLoading ? '注册中...' : '注册' }}
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';
import { ElMessage, ElForm, ElFormItem, ElInput, ElButton } from 'element-plus';
import { User, Lock } from '@element-plus/icons-vue';
import router from '@/router/index.js';
import { useUserInfo } from "@/stores/info.js";
import { testAddUser } from '@/apis/api.js';

const userInfo = useUserInfo();

const registerForm = ref(null);
const formData = reactive({
  username: '',
  password: '',
  confirmPassword: ''
});
const isLoading = ref(false);
const usernameTaken = ref(false);

const rules = reactive({
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 6, max: 30, message: '用户名长度应在6到30个字符之间', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 10, message: '密码长度应在6到10个字符之间', trigger: 'blur' },
    { validator: validatePassword, trigger: 'blur' }
  ],
  confirmPassword: [
    { required: true, message: '请确认密码', trigger: 'blur' },
    { validator: confirmPasswordsMatch, trigger: 'blur' }
  ]
});

// 在 setup() 内部定义函数
function validatePassword(rule, value, callback) {
  const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,10}$/;
  if (!regex.test(value)) {
    callback(new Error('密码必须包含6到10位的大小写字母和数字'));
  } else {
    callback();
  }
}

function confirmPasswordsMatch(rule, value, callback) {
  if (value !== formData.password) {
    callback(new Error('两次输入的密码不匹配'));
  } else {
    callback();
  }
}


const handleSubmit = async () => {
  try {
    await registerForm.value.validate();
    isLoading.value = true;
    console.log(userInfo.email)
    console.log(formData.username)
    console.log(formData.password)
    const addResult = await testAddUser(userInfo.email, formData.username, formData.password);

    if (addResult.status == '1') {

      userInfo.username = formData.username;
      userInfo.password = formData.password;
      usernameTaken.value = false;
      console.log('注册成功!');
      ElMessage.success('注册成功');
      goToRegister()
    } else {
      usernameTaken.value = true;
      ElMessage.error('注册失败: 用户名重复');
    }
  } catch (error) {
    ElMessage.error('注册失败，请检查您的凭证');
  } finally {
    isLoading.value = false;
  }
};

const goToRegister = () => {
  router.push('/layout/question')
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

.username-taken-message {
  color: red;
  margin-top: 5px;
}
</style>