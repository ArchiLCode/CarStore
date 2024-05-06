<template>
  <div class="home">
    <div class="title">
      <h1>Автомобили</h1>
      <div class="sorting">
        <button @click="sortByName($event)" class="sort-btn">
          По названию
        </button>
        <button @click="sortByPrice($event)" class="sort-btn">По цене</button>
        <button @click="sortByDefault($event)" class="sort-btn active">
          По умолчанию
        </button>
      </div>
    </div>
    <div class="pizza-list">
      <CarCard v-for="car in this.response" :key="car.id" :car="car" />
    </div>
  </div>
</template>

<script>
import CarCard from "@/components/CarCard.vue";
import AXIOS from "@/http-common.js";

export default {
  name: "HomeView",
  components: {
    CarCard,
  },
  data() {
    return {
      response: [],
      errors: [],
    };
  },
  methods: {
    axiosGetPizzas() {
      AXIOS.get("http://localhost:9090/pizza-store/pizzas")
        .then((response) => {
          this.response = response.data;
        })
        .catch((e) => {
          this.errors.push(e);
        });
    },
    activateBtn(event) {
      const sortBtns = document.querySelectorAll(".sort-btn");
      for (const btn of sortBtns) {
        btn.classList.remove("active");
      }
      event.target.classList.add("active");
    },
    sortByName(event) {
      this.activateBtn(event);
      AXIOS.get("http://localhost:9090/pizza-store/pizzas/order/name")
        .then((response) => {
          this.response = response.data;
        })
        .catch((e) => {
          this.errors.push(e);
        });
    },
    sortByPrice(event) {
      this.activateBtn(event);
      AXIOS.get("http://localhost:9090/pizza-store/pizzas/order/price")
        .then((response) => {
          this.response = response.data;
        })
        .catch((e) => {
          this.errors.push(e);
        });
    },
    sortByDefault(event) {
      this.activateBtn(event);
      this.axiosGetPizzas();
    },
  },
  created() {
    this.axiosGetPizzas();
  },
};
</script>

<style lang="scss" scoped>
.home {
  .title {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    margin-bottom: 10px;
    h1 {
      font-family: Montserrat;
      font-size: 32px;
      font-weight: 800;
      color: #ff9131;
      margin-bottom: 10px;
    }
    .sorting {
      display: flex;
      flex-direction: row;
      align-items: center;
      justify-content: space-between;
      width: 35%;
      .sort-btn {
        padding: 10px 15px;
        background-color: transparent;
        color: white;
        border-radius: 8px;
        border: 1px solid #ffffff;
        font-size: 15px;
        font-family: Montserrat;
        font-weight: 600;
        cursor: pointer;
      }
      .active {
        border-color: #ff9131;
        background-color: #ff9131;
      }
    }
  }
  .pizza-list {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    justify-items: center;
    grid-gap: 10px;
    padding-bottom: 70px;
  }
}
</style>
