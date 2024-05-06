<template>
  <div class="container">
    <div>
      <div class="img-container">
        <img :src="car.imgLink" alt="Pizza" />
      </div>
      <div class="name">
        <h2>{{ car.name }}</h2>
      </div>
    </div>
    <div class="description">
      <p>{{ car.description }}</p>
    </div>
    <div class="price">
      <h2>{{ car.price }} &#8381;</h2>
      <button @click="addToCart()" id="add-to-cart-btn">В корзину</button>
    </div>
  </div>
</template>

<script>
import AXIOS from "@/http-common.js";

export default {
  name: "Car",
  props: { car: {} },
  data() {
    return {
      userId: localStorage.getItem("userId"),
    };
  },
  methods: {
    addToCart() {
      if (localStorage.getItem("token")) {
        AXIOS.post(
          "http://localhost:9090/pizza-store/user/" +
            this.userId +
            "/pizza/" +
            this.car.id,
          {},
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        ).catch((e) => {
          if (localStorage.getItem("token") && e.response.status == 401) {
            alert("Время вашей сессии истекло!");
          } else if (
            localStorage.getItem("token") &&
            e.response.status == 400
          ) {
            alert("Авто уже в корзине!");
          }
        });
      } else {
        alert("Вы не авторизованы!");
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 20px;
  width: 250px;
  height: 460px;
  font-family: Montserrat;
  border-radius: 20px;
  transition: 0.1s;
  &:hover {
    box-shadow: 0px 2px 24px 2px #00000015;
  }
  img {
    width: 100%;
    border-radius: 25%;
  }
  .name {
    color: #ffffff;
    font-weight: 800;
    h2 {
      margin: 0;
      font-size: 24px;
    }
  }
  .description {
    color: #d4d4d4;
    font-weight: 500;
    font-size: 13px;
    p {
      margin: 0;
    }
  }
  .price {
    color: #231f20;
    font-weight: 700;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    h2 {
      margin: 0;
    }
  }
  #add-to-cart-btn {
    background-color: #ff9131;
    border: none;
    padding: 10px 20px;
    outline: none;
    border-radius: 8px;
    font-weight: 700;
    font-size: 14px;
    color: #fff;
    transition: 0.2s;
    cursor: pointer;
    &:hover {
      color: #473e43;
    }
  }
}
</style>
