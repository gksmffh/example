<template>
  <div>
    <div class="input-group flex-nowrap">
      <span class="input-group-text" id="addon-wrapping">제목</span>
      <input
        type="text"
        v-model="title"
        class="form-control"
        placeholder="제목"
        aria-label="제목"
        aria-describedby="addon-wrapping"
      />
    </div>
    <textarea cols="200" v-model="contents"></textarea>
    <button type="button" @click="putSave">Save</button>
  </div>
</template>
<script>
import axios from "axios";
import VueCookies from "vue-cookies";
export default {
  name: "",
  components: {},
  data() {
    return {
      title: "",
      contents: "",
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    async putSave() {
      try {
        const data = {
          title: this.title,
          contents: this.contents,
          boardType: "COMMUNITY",
        };
        const response = await axios.put("http://localhost:8080/board", data, {
          headers: { Authorization: `Bearer ${VueCookies.get("token")}` },
        });
        console.log(response);

        alert("Success");
        this.$router.push({
          path: "/main",
        });
      } catch (error) {
        alert(error.response.data.error);
      }
    },
  },
};
</script>
