<script setup lang="ts">
import { ref, reactive } from "vue";
import type { Group } from "@/models/group/Group";

const emit = defineEmits<{
  (e: "complete", groupForm: Group): void;
  (e: "cancel"): void;
}>();

const props = defineProps<{
  show: boolean;
}>();

const state = reactive({
  form: {},
});

const show = ref(props.show);
</script>

<template>
  <v-dialog v-model="show">
    <v-card>
      <v-card-text>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua.
      </v-card-text>
      <v-card-actions>
        <v-btn color="primary" block @click="emit('complete', state.form)"
          >Close Dialog</v-btn
        >
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<style lang="scss" scoped></style>
