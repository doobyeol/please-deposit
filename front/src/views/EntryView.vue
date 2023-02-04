<script setup lang="ts">
const handleClickGroup = (grouId: number) => {
  console.log("grouId: ", grouId);
};

const handleClickAddGroup = () => {
  console.log("handleClickAddGroup");
};
</script>

<template>
  <div class="d-flex justify-center align-center flex-column">
    <div class="entry-title">입장할 모임을 선택하세요!</div>
    <div class="pt-5 pb-5">
      <v-card
        width="300"
        class="mt-5 rounded-xl"
        variant="flat"
        @click="handleClickGroup(1)"
      >
        <v-card-item>
          <v-card-title class="group-title">ReturnTrue</v-card-title>
        </v-card-item>
        <v-card-text>
          <div>모임장: 두별</div>
          <div>인원: 10 명</div>
          <div>만든날: 2023-01-01</div>
        </v-card-text>
      </v-card>
    </div>
    <div>
      <v-btn icon="mdi-plus" flat @click="handleClickAddGroup"></v-btn>
    </div>
    <div class="pt-2 text-disabled">모임이 없다면 새로 만들어보세요!</div>
  </div>
</template>

<style lang="scss" scoped>
.entry-title {
  padding-top: 50px;
  font-size: 25px;
  font-weight: bold;
}
.group-title {
  font-weight: bold;
}
</style>
