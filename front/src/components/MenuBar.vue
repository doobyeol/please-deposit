<script setup lang="ts">
import { onMounted, ref } from "vue";

const toggle = ref(0);

onMounted(() => {
  toggle.value = 0;
});
</script>

<template>
  <v-footer id="footer" :app="true">
    <v-btn-toggle
      v-model="toggle"
      id="btnGroup"
      height="60px"
      variant="plain"
      color="blue-darken-1"
      :mandatory="true"
    >
      <v-btn
        class="flex-1 menu-btn"
        stacked
        prepend-icon="mdi-receipt-text-outline"
      >
        지출
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
  background-color: #c0dbc9;

  #btnGroup {
    display: flex;
    width: 100%;
    height: 80px;

    .v-btn--variant-plain {
      opacity: 0.4;
    }
    // .v-btn--variant-plain:focus {
    //   opacity: 1;
    // }
    .v-btn--active {
      opacity: 1;
    }

    // .menu-btn {
    //   background-color: white;
    // }
    // .menu-btn:active {
    //   background-color: white;
    // }
    // .menu-btn:focus {
    //   background-color: white;
    // }
    // .v-btn__overlay {
    //   opacity: 1;
    // }
  }
}
</style>
