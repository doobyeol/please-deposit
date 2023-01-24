<script setup lang="ts">
const handleLoginWithKakao = () => {
  location.href =
    "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=f59502021c10423854ce6680682e1ebf&redirect_uri=http://localhost:8080/login/kakao/callback";
};
</script>

<template>
  <main>
    <h1 class="p-4">This is an Login page</h1>
    <v-btn color="yellow" @click="handleLoginWithKakao">Kakao 로그인</v-btn>
  </main>
</template>

<style lang="scss" scoped></style>
