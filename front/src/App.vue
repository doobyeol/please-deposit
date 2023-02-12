<script setup lang="ts">
import NavBar from "@/components/NavBar.vue";
import MenuBar from "@/components/MenuBar.vue";
import { useViewStore } from "@/stores/common/viewStore";
import { storeToRefs } from "pinia";

const viewStore = useViewStore();
const { isShowNavBar, isShowMenuBar } = storeToRefs(viewStore);
</script>

<template>
  <v-app>
    <NavBar v-if="isShowNavBar"></NavBar>

    <v-main class="app-main">
      <v-container :class="{ 'pa-0-important': !isShowNavBar }">
        <RouterView />
      </v-container>
    </v-main>

    <MenuBar v-if="isShowMenuBar"></MenuBar>
  </v-app>
</template>

<style lang="scss" scoped>
.app-main {
  background-color: #cfe3d6bf;
}
.pa-0-important {
  padding: 0 !important;
}
.app-bar {
  display: flex;
  padding: 10px;
  justify-content: center;
}
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
