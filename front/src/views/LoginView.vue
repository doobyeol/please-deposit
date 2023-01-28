<script setup lang="ts">
const handleLoginWithKakao = () => {
  location.href =
    "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=f59502021c10423854ce6680682e1ebf&redirect_uri=http://localhost:8080/login/kakao/callback";
};
</script>

<template>
  <div class="d-flex justify-center align-center">
    <div>
      <v-img
        src="/src/assets/images/kakao_login.png"
        @click="handleLoginWithKakao"
        width="200px"
      ></v-img>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.height-100 {
  height: 100%;
}
</style>
