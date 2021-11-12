<template>
  <div>
    <div class="input-group flex-nowrap">
      <span class="input-group-text" id="addon-wrapping">제목</span>
      <input
        type="text"
        v-model="board.title"
        class="form-control"
        placeholder="제목"
        aria-label="제목"
        aria-describedby="addon-wrapping"
      />
    </div>
    <textarea cols="200" v-model="board.contents"></textarea>
    <button type="button" @click="putSave">Save</button>
    <button type="button" @click="goBack">back</button>
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
      sampleData: "",
      board: {},
    };
  },
  setup() {},
  created() {
    this.getDetail();
  },
  mounted() {},
  unmounted() {},
  methods: {
    async getDetail() {
      try {
        const id = this.$route.params.id;

        const response = await axios.get(`http://localhost:8080/board/${id}`, {
          headers: { Authorization: `Bearer ${VueCookies.get("token")}` },
        });
        console.log(response);

        this.board = response.data.data;
      } catch (error) {
        alert(error.response.data.error);
      }
    },
    async putSave() {
      try {
        const id = this.$route.params.id;
        const data = {
          boardSeq: id,
          title: this.board.title,
          contents: this.board.contents,
          boardType: "COMMUNITY",
        };

        const response = await axios.put("http://localhost:8080/board/", data, {
          headers: { Authorization: `Bearer ${VueCookies.get("token")}` },
        });
        console.log(response);

        this.$router.push({
          path: `/detail/${id}`,
        });
      } catch (error) {
        alert(error.response.data.error);
      }
    },
    goBack() {
      const id = this.$route.params.id;
      this.$router.push({
        path: `/detail/${id}`,
      });
    },
  },
};
</script>
