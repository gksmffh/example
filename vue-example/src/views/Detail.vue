<template>
  <div>
    <div class="input-group flex-nowrap">
      <span class="input-group-text" id="addon-wrapping">제목</span>
      <input
        type="text"
        v-model="board.title"
        readonly
        class="form-control"
        placeholder="제목"
        aria-label="제목"
        aria-describedby="addon-wrapping"
      />
    </div>
    <div>{{ changeDate(board.regDate / 1000) }}</div>
    <textarea cols="200" v-model="board.contents" readonly></textarea>

    <button type="button" @click="goMain">메인화면</button>
    <button type="button" @click="edit(board.boardSeq)">수정</button>
    <button type="button" @click="boardDelete">삭제</button>
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
    changeDate(t) {
      var myDate = new Date(t * 1000);
      var date =
        myDate.getFullYear() +
        "-" +
        (myDate.getMonth() + 1) +
        "-" +
        myDate.getDate() +
        " " +
        myDate.getHours() +
        "시" +
        myDate.getMinutes() +
        "분";
      return date;
    },
    goMain() {
      this.$router.push({
        path: "/main",
      });
    },
    edit(boardSeq) {
      this.$router.push({
        path: `/edit/${boardSeq}`,
      });
    },
    async boardDelete() {
      try {
        const id = this.$route.params.id;

        const response = await axios.delete(
          `http://localhost:8080/board/${id}`,
          {
            headers: { Authorization: `Bearer ${VueCookies.get("token")}` },
          }
        );
        console.log(response);
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
