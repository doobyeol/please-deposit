<script setup lang="ts">
// 1. store 에 token 이 있는지 확인
// 2. store 에 token 없으면 cookie 에서 확인
// 3. token 이 있으면 API 호출
// 4. token 이 없으면 팝업 띄우고 로그인 페이지로 이동
</script>

<template>
  <main>
    <h1>진입 페이지! 여기서 입장할 모임 선택해야 함</h1>
    <v-icon icon="mdi-home" />
  </main>
</template>

<style lang="scss" scoped></style>
