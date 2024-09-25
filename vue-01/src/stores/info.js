
// export const useUserInfo = defineStore('info', () => {
//
//   let username = ref('')
//
//   let password = ref('')
//
//   let email = ref('')
//
//   let identity = ref('')
//
//
//   return { username, password, email, identity }
// })
import { defineStore } from 'pinia';
import { ref, watch } from 'vue';

export const useUserInfo = defineStore('info', () => {
  let username = ref(localStorage.getItem('username') || '');
  let password = ref(localStorage.getItem('password') || '');
  let email = ref(localStorage.getItem('email') || '');
  let identity = ref(localStorage.getItem('identity') || '');

  // 监控并更新 localStorage
  watch(username, (newVal) => {
    localStorage.setItem('username', newVal);
  });

  watch(password, (newVal) => {
    localStorage.setItem('password', newVal);
  });

  watch(email, (newVal) => {
    localStorage.setItem('email', newVal);
  });

  watch(identity, (newVal) => {
    localStorage.setItem('identity', newVal);
  });

  return { username, password, email, identity };
});
