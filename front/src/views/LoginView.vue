<script setup lang="ts">
import { useSessionStore } from "@/stores/common/sessionStore";
import router from "@/router";
import { ref } from "vue";

const { login } = useSessionStore();
const isLoading = ref(false);

const init = async () => {
  isLoading.value = true;
  const isLogginIn = await login();
  if (isLogginIn) {
    router.push("entry");
  }
  isLoading.value = false;
};

init();

const handleLoginWithKakao = () => {
  location.href =
    "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=f59502021c10423854ce6680682e1ebf&redirect_uri=http://localhost:8080/login/kakao/callback";
};
</script>

<template>
  <div class="login-area d-flex justify-center align-center">
    <div class="d-flex justify-center align-center flex-column">
      <v-spacer></v-spacer>
      <div class="d-flex justify-center align-center flex-column pb-10">
        <v-img
          src="/src/assets/images/logo.png"
          width="150px"
          class="logo"
        ></v-img>
        <div class="login-title">입금부탁</div>
      </div>
      <div class="pt-16">
        <v-progress-circular
          v-if="isLoading"
          indeterminate
          color="amber"
        ></v-progress-circular>
        <v-img
          v-else
          src="/src/assets/images/kakao_login.png"
          width="200px"
          class="pt-5 login-btn"
          @click="handleLoginWithKakao"
        ></v-img>
      </div>
      <v-spacer></v-spacer>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.login-area {
  height: 100vh;

  background: linear-gradient(-45deg, #188ca7, #7fe3d0);
  background-size: 100% 100%;
}
.login-title {
  padding-top: 35px;
  text-align: center;
  color: white;
  font-size: 30px;
  font-weight: bold;
}
.login-btn {
  cursor: pointer;
}
.logo {
  position: relative;
  animation: motion 0.5s linear 0s infinite alternate;
  top: 0;
}

@keyframes motion {
  0% {
    top: 0px;
  }
  100% {
    top: 10px;
  }
}
</style>
