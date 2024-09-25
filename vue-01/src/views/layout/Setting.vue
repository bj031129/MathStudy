<template>
  <div class="change-password-container setting-container"  >
    <el-card class="change-password-card">
      <template #header>
        <h2 class="change-password-title">修改密码</h2>
      </template>
      <el-form :model="formData" :rules="rules" ref="changePasswordForm" @submit.prevent="handlePasswordChange">
        <el-form-item prop="currentPassword">
          <el-input v-model="formData.currentPassword" type="password" placeholder="当前密码" show-password>
            <template #prefix>
              <el-icon><Lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="newPassword">
          <el-input v-model="formData.newPassword" type="password" placeholder="新密码" show-password>
            <template #prefix>
              <el-icon><Lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="confirmNewPassword">
          <el-input v-model="formData.confirmNewPassword" type="password" placeholder="确认新密码" show-password>
            <template #prefix>
              <el-icon><Lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" native-type="submit" :loading="isLoading" class="change-password-button">
            {{ isLoading ? '修改中...' : '修改密码' }}
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';
import { ElMessage, ElForm, ElFormItem, ElInput, ElButton, ElIcon } from 'element-plus';
import { Lock } from '@element-plus/icons-vue';
import {testChangePassword} from "@/apis/api.js";
import { useUserInfo } from "@/stores/info.js";
import router from "@/router/index.js";

const userInfo = useUserInfo();

const formData = reactive({
  currentPassword: '',
  newPassword: '',
  confirmNewPassword: ''
});

const validateOldPassword = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请输入原密码'));
  } else if (value !== userInfo.password) {
    callback(new Error('原密码错误'));
  } else {
    callback();
  }
};

const validateNewPassword = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请输入新密码'));
  } else if (!/^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)[A-Za-z\d]{6,10}$/.test(value)) {
    callback(new Error('密码新必须包含6到10位的大小写字母和数字'));
  } else {
    callback();
  }
};

const validatePasswordMatch = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请再次输入新密码'));
  } else if (value !== formData.newPassword) {
    callback(new Error('两次输入的密码不一致'));
  } else {
    callback();
  }
};

const rules = reactive({
  currentPassword: [
    { required: true, message: '请输入当前密码', trigger: 'blur' },
    { min: 6, max: 10, message: '密码长度应在6到10个字符之间', trigger: 'blur' },
    { validator: validateOldPassword, trigger: 'blur' }
  ],
  newPassword: [
    { required: true, message: '请输入新密码', trigger: 'blur' },
    { validator: validateNewPassword, trigger: 'blur' }
  ],
  confirmNewPassword: [
    { required: true, message: '请再次输入新密码', trigger: 'blur' },
    { validator: validatePasswordMatch, trigger: 'blur' }
  ]
});


const changePasswordForm = ref(null);
const isLoading = ref(false);

const goToRegister = () => {
  router.push('/layout/question')
}

const handlePasswordChange = async () => {
  try {
    // 验证表单
    await changePasswordForm.value.validate();

    // 设置加载状态
    isLoading.value = true;
    // 模拟修改密码的API调用
    const result = await testChangePassword(userInfo.username, formData.currentPassword, formData.newPassword);

    if (result.status == '1') {
      console.log('密码修改成功!');
      ElMessage.success('密码修改成功');
      userInfo.password= formData.newPassword
      alert('密码修改成功')
      goToRegister();
    } else {
      console.error('密码修改失败: ', result.message);
      ElMessage.error(result.message);
    }
  } catch (error) {
    console.error('原密码错误或者找不到传入用户名入:', error);
    ElMessage.error('原密码错误');
    alert('原密码错误')
  } finally {
    // 无论成功还是失败，都需要重置加载状态
    isLoading.value = false;
  }


};

</script>

<style scoped>

.setting-container {
  width: 90%; /* 占据父元素的90%宽度 */
  min-height: 80vh; /* 最小高度为视口高度的80% */
  background-color: #f0f2f5;
  display: flex;
  justify-content: center;
  align-items: center;
}

.change-password-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5;
}

.change-password-card {
  width: 100%;
  max-width: 400px;
}

.change-password-title {
  text-align: center;
  font-size: 24px;
  color: #303133;
}

.change-password-button {
  width: 100%;
}
</style>