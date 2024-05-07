<template>
  <div class="order-item">
    <div class="img-container">
      <img :src="this.order.imgLink" alt="Car" />
    </div>
    <div class="order-text">
      <div class="order-name">{{ this.order.name }}</div>
      <div class="description">{{ this.order.description }}</div>
    </div>
    <div class="price-container">
      <div class="price">
        {{ this.splitPrice(this.order.price * this.amount) }} &#8381;
      </div>
      <div class="counter-container">
        <button @click="subOne($event)" class="sub-one button">–</button>
        <div class="amount">{{ this.amount }}</div>
        <button @click="addOne($event)" class="add-one button">+</button>
      </div>
    </div>
    <i
      @click="removeItem($event)"
      class="rem-item fa-regular fa-circle-xmark"
    ></i>
  </div>
</template>

<script>
import AXIOS from "@/http-common";

export default {
  name: "OrderItem",
  props: { order: {} },
  data() {
    return {
      amount: 1,
    };
  },
  methods: {
    removeItem(event) {
      document
        .querySelector(".orders-list")
        .removeChild(event.target.closest(".order-item"));
      AXIOS.delete(
        "https://car-store-backend-amhi.onrender.com/car-store/user/" +
          localStorage.getItem("userId") +
          "/car/" +
          this.order.id,
        {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        }
      );
    },
    addOne(event) {
      if (this.amount >= 1 && this.amount < 20) this.amount++;
      if (this.amount === 20) {
        event.target.disabled = true;
        event.target.classList.add("disabled");
      }
      const subOne = event.target.previousSibling.previousSibling;
      subOne.disabled = false;
      subOne.classList.remove("disabled");
    },
    subOne(event) {
      if (this.amount > 1) this.amount--;
      else {
        event.target.disabled = true;
        event.target.classList.add("disabled");
      }
      if (this.amount <= 20) {
        const addOne = event.target.nextSibling.nextSibling;
        addOne.disabled = false;
        addOne.classList.remove("disabled");
      }
    },
    splitPrice(price) {
      const reversedArr = String(price).split("").reverse();
      let newString = [];
      for (let i = 0; i < reversedArr.length; i++) {
        newString.push(String(reversedArr[i]));
        if ((i + 1) % 3 === 0) {
          newString.push(" ");
        }
      }
      return newString.reverse().join("");
    },
  },
};
</script>

<style lang="scss" scoped>
.img-container {
  width: 25%;
  img {
    width: 100%;
  }
  padding-right: 30px;
}
.order-item {
  font-family: Montserrat;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  padding: 30px;
  border-radius: 20px;
  transition: 0.1s;
  margin-bottom: 20px;
  background-color: #3a3a3a;
  &:hover {
    box-shadow: 0px 2px 24px 2px #f4f4f415;
  }

  .order-text {
    max-width: 300px;
  }
  .order-name {
    color: #ffffff;
    font-weight: 800;
    font-size: 24px;
    margin-bottom: 20px;
  }
  .price-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    gap: 20px;
    width: 200px;
  }
  .description {
    color: #ababab;
    font-weight: 500;
    font-size: 13px;
  }
  .price {
    color: #ff9131;
    font-size: 24px;
    font-weight: 800;
    text-align: center;
  }
  .counter-container {
    display: flex;
    flex-direction: row;
    align-items: center;
    background: #f3f3f7;
    border-radius: 8px;
    button {
      background: none;
      border: none;
      padding: 10px 15px;
      color: #696f7a;
      font-weight: 700;
      font-size: 18px;
      cursor: pointer;
    }
    .amount {
      text-align: center;
      width: 25px;
      font-size: 18px;
      color: #696f7a;
      font-weight: 700;
    }
    .disabled {
      color: #9da5b3;
    }
  }
  .fa-circle-xmark {
    font-size: 28px;
    transition: 0.1s;
    cursor: pointer;
    color: #696f7a;
    &:hover {
      color: #ff9131;
    }
  }
}
</style>
