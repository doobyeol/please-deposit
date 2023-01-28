<script setup lang="ts">
import { ref } from "vue";

const toggle = ref(0);
</script>

<template>
  <v-footer id="footer" :app="true">
    <v-btn-toggle
      v-model="toggle"
      color="info"
      rounded="0"
      id="btnGroup"
      height="80px"
    >
      <v-btn class="flex-1 menu-btn" stacked prepend-icon="mdi-home">
        홈
      </v-btn>
      <v-btn class="flex-1 menu-btn" stacked prepend-icon="mdi-cog">
        설정
      </v-btn>
    </v-btn-toggle>
  </v-footer>
</template>

<style lang="scss" scoped>
#footer {
  padding: 0;

  #btnGroup {
    display: flex;
    width: 100%;
    height: 80px;

    .menu-btn {
      background-color: #c2c2c238;
    }
  }
}
</style>
