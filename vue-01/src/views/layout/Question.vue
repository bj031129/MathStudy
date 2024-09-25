<!--<template>-->
<!--  <div class="setting-container">-->
<!--    <el-card v-if="questions.length === 0" class="question-card">-->
<!--      <template #header>-->
<!--        <h2 class="setting-title">选择题目类型和数量</h2>-->
<!--      </template>-->
<!--      <el-form :model="formData" :rules="rules" ref="formRef" @submit.prevent="handleSubmit">-->
<!--        <div class="horizontal-form-items">-->
<!--          <el-form-item prop="level" class="horizontal-form-item">-->
<!--            <el-select v-model="formData.level" placeholder="请选择题目类型">-->
<!--              <el-option label="小学" value="1"></el-option>-->
<!--              <el-option label="初中" value="2"></el-option>-->
<!--              <el-option label="高中" value="3"></el-option>-->
<!--            </el-select>-->
<!--          </el-form-item>-->
<!--          <el-form-item prop="numberOfQuestions" class="horizontal-form-item">-->
<!--            <el-input-number v-model="formData.numberOfQuestions"  style="width: 100%;"  @blur="handleBlur">-->
<!--              <template #prepend>生成的题目数量</template>-->
<!--            </el-input-number>-->
<!--          </el-form-item>-->
<!--        </div>-->
<!--        <el-form-item>-->
<!--          <el-button type="primary" native-type="submit" :loading="isLoading" class="generate-button">-->
<!--            {{ isLoading ? '生成中...' : '生成题目' }}-->
<!--          </el-button>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--    </el-card>-->
<!--&lt;!&ndash;    <el-card v-if="questions.length > 0" class="question-card" style="width: 80%; max-width: 600px; margin-top: 20px;">&ndash;&gt;-->
<!--&lt;!&ndash;      <template #header>&ndash;&gt;-->
<!--&lt;!&ndash;        <h2 class="question-title">当前考试难度为{{formData.level == 1 ? '小学' : formData.level == 2 ? '初中' : '高中'}}</h2>&ndash;&gt;-->
<!--&lt;!&ndash;      </template>&ndash;&gt;-->
<!--&lt;!&ndash;      <div v-if="currentQuestionIndex >= 0 && currentQuestionIndex < questions.length" class="question">&ndash;&gt;-->
<!--&lt;!&ndash;        <h3>第{{ currentQuestionIndex + 1 }}题</h3>&ndash;&gt;-->
<!--&lt;!&ndash;        <p>{{ questions[currentQuestionIndex].question }}</p>&ndash;&gt;-->
<!--&lt;!&ndash;        <el-radio-group v-model="userAnswers[currentQuestionIndex]">&ndash;&gt;-->
<!--&lt;!&ndash;          <el-radio v-for="(option, index) in questions[currentQuestionIndex].options" :label="index">&ndash;&gt;-->
<!--&lt;!&ndash;            {{ option }}&ndash;&gt;-->
<!--&lt;!&ndash;          </el-radio>&ndash;&gt;-->
<!--&lt;!&ndash;        </el-radio-group>&ndash;&gt;-->
<!--&lt;!&ndash;        <el-button type="primary" @click="nextQuestion">下一题</el-button>&ndash;&gt;-->
<!--&lt;!&ndash;      </div>&ndash;&gt;-->
<!--&lt;!&ndash;      <div v-if="currentQuestionIndex >= questions.length" class="result">&ndash;&gt;-->
<!--&lt;!&ndash;        <h3>测试完成！</h3>&ndash;&gt;-->
<!--&lt;!&ndash;        <p>您的得分：{{ score }} / {{ 100}}</p>&ndash;&gt;-->
<!--&lt;!&ndash;        <button @click="clearData">退出</button>&ndash;&gt;-->
<!--&lt;!&ndash;      </div>&ndash;&gt;-->
<!--&lt;!&ndash;    </el-card>&ndash;&gt;-->
<!--    <el-card v-if="questions.length > 0" class="question-card" style="width: 80%; max-width: 600px; margin: 20px auto;">-->
<!--      <template #header>-->
<!--        <h2 class="question-title">当前考试难度为{{ formData.level == 1 ? '小学' : formData.level == 2 ? '初中' : '高中' }}</h2>-->
<!--      </template>-->

<!--      <div class="question-content">-->
<!--        <div v-if="currentQuestionIndex >= 0 && currentQuestionIndex < questions.length" class="question">-->
<!--          <h3>第{{ currentQuestionIndex + 1 }}题</h3>-->
<!--          <p>{{ questions[currentQuestionIndex].question }}</p>-->
<!--          <el-radio-group v-model="userAnswers[currentQuestionIndex]">-->
<!--            <el-radio v-for="(option, index) in questions[currentQuestionIndex].options" :key="index" :label="index">-->
<!--              {{ option }}-->
<!--            </el-radio>-->
<!--          </el-radio-group>-->
<!--          <el-button type="primary" @click="nextQuestion">下一题</el-button>-->
<!--        </div>-->

<!--        <div v-if="currentQuestionIndex >= questions.length" class="result">-->
<!--          <h3>测试完成！</h3>-->
<!--          <p>您的得分：{{ score }} / {{ 100 }}</p>-->
<!--          <el-button type="default" @click="clearData">退出</el-button>-->
<!--        </div>-->
<!--      </div>-->
<!--    </el-card>-->
<!--  </div>-->
<!--</template>-->

<!--<script setup>-->
<!--import { ref, reactive, onMounted, watch } from 'vue';-->
<!--import { ElMessage, ElForm, ElFormItem, ElSelect, ElOption, ElInputNumber, ElButton, ElRadioGroup, ElRadio } from 'element-plus';-->
<!--import { testGenerateQuestions } from '@/apis/api.js'; // 假设这是获取题目的API-->

<!--let formRef = ref(null);-->
<!--let formData = reactive({-->
<!--  level: '1',-->
<!--  numberOfQuestions: 15-->
<!--});-->

<!--// 监控题目数量的变化-->
<!--const handleBlur = () => {-->
<!--  if (formData.numberOfQuestions < 10) {-->
<!--    alert('题目数量应该在10~30之间');-->
<!--    formData.numberOfQuestions = 10; // 设置为最小值-->
<!--  } else if (formData.numberOfQuestions > 30) {-->
<!--    alert('题目数量应该在10~30之间');-->
<!--    formData.numberOfQuestions = 30; // 设置为最大值-->
<!--  }-->
<!--};-->

<!--const rules = reactive({-->
<!--  level: [{ required: true, message: '请选择题目类型', trigger: 'change' }],-->
<!--  numberOfQuestions: [-->
<!--    { required: true, message: '请输入题目数量', trigger: 'blur' },-->
<!--    {-->
<!--      validator: (rule, value, callback) => {-->
<!--        if (value < 10 || value > 30) {-->
<!--          callback(new Error('题目数量应在10到30之间'));-->
<!--        } else {-->
<!--          callback();-->
<!--        }-->
<!--      }-->
<!--    }-->
<!--  ]-->
<!--});-->

<!--const handleSubmit = async () => {-->
<!--  try {-->
<!--    isLoading.value = true;-->
<!--    questions.value = await testGenerateQuestions(formData.level, formData.numberOfQuestions);-->
<!--    currentQuestionIndex.value = 0;-->
<!--    userAnswers.value = Array.from({ length: formData.numberOfQuestions }, () => null);-->
<!--    score.value = 0-->
<!--  } catch (error) {-->
<!--    console.error('获取题目时出错:', error);-->
<!--    ElMessage.error('获取题目失败，请稍后再试');-->
<!--  } finally {-->
<!--    isLoading.value = false;-->
<!--  }-->
<!--};-->

<!--let questions = ref([]);-->
<!--let userAnswers = ref([]);-->
<!--let currentQuestionIndex = ref(0); // 当前题目索引-->

<!--let score = ref(0); // 得分-->

<!--let isLoading = ref(false);-->

<!--const clearData = () => {-->
<!--  questions.value = [];-->
<!--  userAnswers.value = [];-->
<!--  currentQuestionIndex.value = 0;-->
<!--  formData.level = '1';-->
<!--  formData.numberOfQuestions = 0;-->
<!--}-->

<!--const nextQuestion = () => {-->
<!--  if (currentQuestionIndex.value + 1 < questions.value.length) {-->
<!--    currentQuestionIndex.value += 1;-->
<!--  } else {-->
<!--    currentQuestionIndex.value += 1;-->
<!--    calculateScore();-->
<!--  }-->
<!--};-->

<!--const calculateScore = () => {-->
<!--  let correctAnswers = 0;-->
<!--  for (let i = 0; i < questions.value.length; i++) {-->
<!--    if (userAnswers.value[i] == questions.value[i].answer) {-->
<!--      correctAnswers++;-->
<!--    }-->
<!--  }-->
<!--  score.value = correctAnswers / questions.value.length * 100;-->
<!--  score.value = Math.floor(score.value);-->
<!--};-->

<!--onMounted(() => {-->
<!--  // 初始化操作-->
<!--});-->
<!--</script>-->

<!--<style scoped>-->
<!--.setting-container {-->
<!--  width: 90%; /* 占据父元素的90%宽度 */-->
<!--  min-height: 80vh; /* 最小高度为视口高度的80% */-->
<!--  background-color: #f0f2f5;-->
<!--  display: flex;-->
<!--  justify-content: center;-->
<!--  align-items: center;-->
<!--}-->

<!--.horizontal-form-items {-->
<!--  display: flex;-->
<!--  gap: 10px; /* 间距 */-->
<!--}-->

<!--.horizontal-form-item {-->
<!--  flex: 1; /* 让每个表单项占据相同的宽度 */-->
<!--}-->


<!--.question-card {-->
<!--  width: 100%;-->
<!--  max-width: 600px;-->
<!--  margin-top: 20px;-->
<!--  padding: 20px;-->
<!--  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);-->
<!--  text-align: center; /* 内容居中 */-->
<!--}-->

<!--.setting-title,-->
<!--.question-title {-->
<!--  text-align: center;-->
<!--  font-size: 24px;-->
<!--  color: #303133;-->
<!--  margin-bottom: 20px;-->
<!--  margin-bottom: 20px; /* 调整标题与内容的间距 */-->
<!--}-->

<!--.generate-button {-->
<!--  width: 100%;-->
<!--}-->

<!--.custom-input-number .el-input-number__increase,-->
<!--.custom-input-number .el-input-number__decrease {-->
<!--  display: none !important; /* 隐藏箭头 */-->
<!--}-->

<!--.el-select .el-input .el-input__inner {-->
<!--  background-color: white !important; /* 改变下拉框背景色 */-->
<!--}-->
<!--</style>-->


<template>
  <div class="setting-container">
    <el-card v-if="questions.length === 0" class="question-card">
      <template #header>
        <h2 class="setting-title">选择题目类型和数量</h2>
      </template>
      <el-form :model="formData" :rules="rules" ref="formRef" @submit.prevent="handleSubmit">
        <div class="horizontal-form-items">
          <el-form-item prop="level" class="horizontal-form-item">
            <el-select v-model="formData.level" placeholder="请选择题目类型">
              <el-option label="小学" value="1"></el-option>
              <el-option label="初中" value="2"></el-option>
              <el-option label="高中" value="3"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="numberOfQuestions" class="horizontal-form-item">
            <el-input-number v-model="formData.numberOfQuestions" style="width: 100%;" @blur="handleBlur">
              <template #prepend>生成的题目数量</template>
            </el-input-number>
          </el-form-item>
        </div>
        <el-form-item>
          <el-button type="primary" native-type="submit" :loading="isLoading" class="generate-button">
            {{ isLoading ? '生成中...' : '生成题目' }}
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card v-if="questions.length > 0" class="question-card" style="margin: 20px auto;">
      <template #header>
        <h2 class="question-title">当前考试难度为{{ formData.level == 1 ? '小学' : formData.level == 2 ? '初中' : '高中' }}</h2>
      </template>

      <div class="question-content">
        <div v-if="currentQuestionIndex >= 0 && currentQuestionIndex < questions.length" class="question">
          <h3 class="question-number">第{{ currentQuestionIndex + 1 }}题</h3>
          <p class="question-text">{{ questions[currentQuestionIndex].question }}</p>
          <el-radio-group v-model="userAnswers[currentQuestionIndex]">
            <el-radio v-for="(option, index) in questions[currentQuestionIndex].options" :key="index" :label="index" class="option-item">
              {{ option }}
            </el-radio>
          </el-radio-group>
          <el-button type="primary" @click="nextQuestion" class="next-button">下一题</el-button>
        </div>

        <div v-if="currentQuestionIndex >= questions.length" class="result">
          <h3>测试完成！</h3>
          <p>您的得分：{{ score }} / {{ 100 }}</p>
          <el-button type="default" @click="clearData">退出</el-button>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue';
import { ElMessage } from 'element-plus';
import { testGenerateQuestions } from '@/apis/api.js'; // 假设这是获取题目的API
import {useUserInfo} from "@/stores/info.js";

let formRef = ref(null);
let formData = reactive({
  level: '1',
  numberOfQuestions: 15
});

// 监控题目数量的变化
const handleBlur = () => {
  if (formData.numberOfQuestions < 10) {
    alert('题目数量应该在10~30之间');
    formData.numberOfQuestions = 10; // 设置为最小值
  } else if (formData.numberOfQuestions > 30) {
    alert('题目数量应该在10~30之间');
    formData.numberOfQuestions = 30; // 设置为最大值
  }
};

const rules = reactive({
  level: [{ required: true, message: '请选择题目类型', trigger: 'change' }],
  numberOfQuestions: [
    { required: true, message: '请输入题目数量', trigger: 'blur' },
    {
      validator: (rule, value, callback) => {
        if (value < 10 || value > 30) {
          callback(new Error('题目数量应在10到30之间'));
        } else {
          callback();
        }
      }
    }
  ]
});

const handleSubmit = async () => {
  try {
    isLoading.value = true;
    questions.value = await testGenerateQuestions(formData.level, formData.numberOfQuestions);
    currentQuestionIndex.value = 0;
    userAnswers.value = Array.from({ length: formData.numberOfQuestions }, () => null);
    score.value = 0
  } catch (error) {
    console.error('获取题目时出错:', error);
    ElMessage.error('获取题目失败，请稍后再试');
  } finally {
    isLoading.value = false;
  }
};

let questions = ref([]);
let userAnswers = ref([]);
let currentQuestionIndex = ref(0); // 当前题目索引
let score = ref(0); // 得分
let isLoading = ref(false);

const clearData = () => {
  questions.value = [];
  userAnswers.value = [];
  currentQuestionIndex.value = 0;
  formData.level = '1';
  formData.numberOfQuestions = 0;
}

const nextQuestion = () => {
  if (currentQuestionIndex.value + 1 < questions.value.length) {
    currentQuestionIndex.value += 1;
  } else {
    currentQuestionIndex.value += 1;
    calculateScore();
  }
};

const calculateScore = () => {
  let correctAnswers = 0;
  for (let i = 0; i < questions.value.length; i++) {
    if (userAnswers.value[i] == questions.value[i].answer) {
      correctAnswers++;
    }
  }
  score.value = Math.floor((correctAnswers / questions.value.length) * 100);
};

onMounted(() => {
  // 初始化操作
});
</script>

<style scoped>
.setting-container {
  width: 90%;
  min-height: 80vh;
  background-color: #f0f2f5;
  display: flex;
  justify-content: center;
  align-items: center;
}

.horizontal-form-items {
  display: flex;
  gap: 10px; /* 间距 */
}

.horizontal-form-item {
  flex: 1; /* 让每个表单项占据相同的宽度 */
}

.question-card {
  width: 100%;
  max-width: 600px;
  margin-top: 20px;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center; /* 内容居中 */
}

.setting-title,
.question-title {
  font-size: 24px;
  color: #303133;
  margin-bottom: 20px; /* 调整标题与内容的间距 */
}

.question-number {
  margin: 10px 0; /* 问题标题上下间距 */
}

.question-text {
  margin-bottom: 15px; /* 问题和选项之间的间距 */
}

.option-item {
  margin-bottom: 10px; /* 每个选项之间的间距 */
}

.next-button {
  margin-top: 10px; /* 下一题按钮的上边距 */
}

.generate-button {
  width: 100%;
}

.custom-input-number .el-input-number__increase,
.custom-input-number .el-input-number__decrease {
  display: none !important; /* 隐藏箭头 */
}

.el-select .el-input .el-input__inner {
  background-color: white !important; /* 改变下拉框背景色 */
}
</style>
