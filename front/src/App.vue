<script setup lang="ts">
import NavBar from "@/components/NavBar.vue";
import MenuBar from "@/components/MenuBar.vue";
</script>

<template>
  <v-app>
    <NavBar></NavBar>

    <v-main>
      <v-container>
        <RouterView />
      </v-container>
    </v-main>

    <MenuBar></MenuBar>
  </v-app>
</template>

<style lang="scss" scoped>
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
