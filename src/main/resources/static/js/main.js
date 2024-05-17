document.addEventListener("DOMContentLoaded", () => {
    const signupForm = document.getElementById("signupForm");
    const loginForm = document.getElementById("loginForm");
    const orderForm = document.getElementById("orderForm");
    const profileInfo = document.getElementById("profileInfo");
    const ordersTableBody = document.getElementById("ordersTable") ? document.getElementById("ordersTable").querySelector("tbody") : null;

    // 회원가입
    if (signupForm) {
        signupForm.addEventListener("submit", (event) => {
            event.preventDefault();

            const formData = new FormData(signupForm);
            const member = {};
            formData.forEach((value, key) => {
                member[key] = value;
            });

            fetch("/api/signup", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(member)
            }).then(response => {
                if (response.ok) {
                    window.location.href = "/login.html";
                }
            });
        });
    }

    // 로그인
    if (loginForm) {
        loginForm.addEventListener("submit", (event) => {
            event.preventDefault();

            const formData = new FormData(loginForm);
            const credentials = {};
            formData.forEach((value, key) => {
                credentials[key] = value;
            });

            fetch("/api/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(credentials)
            }).then(response => {
                if (response.ok) {
                    window.location.href = "/profile.html";
                }
            });
        });
    }

    // 프로필 정보 가져오기
    if (profileInfo) {
        fetch("/api/profile", {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(response => response.json())
            .then(data => {
                profileInfo.innerHTML = `
                <p>Name: ${data.name}</p>
                <p>Email: ${data.email}</p>
                <p>Phone Number: ${data.phoneNumber}</p>
                <p>Address: ${data.address}</p>
            `;
            });
    }

    // 로그아웃
    window.logout = function() {
        fetch("/api/logout", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(() => {
            window.location.href = "/login.html";
        });
    };

    // 회원탈퇴
    window.deleteAccount = function() {
        fetch("/api/delete", {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(() => {
            window.location.href = "/signup.html";
        });
    };

    // 주문하기
    if (orderForm) {
        orderForm.addEventListener("submit", (event) => {
            event.preventDefault();

            const formData = new FormData(orderForm);
            const order = {};
            formData.forEach((value, key) => {
                order[key] = value;
            });

            fetch("/api/orders/new", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(order)
            }).then(response => {
                if (response.ok) {
                    loadOrders();
                }
            });
        });
    }

    // 주문 목록 가져오기
    if (ordersTableBody) {
        loadOrders();
    }

    function loadOrders() {
        fetch("/api/orders/list", {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(response => response.json())
            .then(data => {
                ordersTableBody.innerHTML = "";
                data.forEach(order => {
                    const row = ordersTableBody.insertRow();
                    row.insertCell(0).innerText = order.id;
                    row.insertCell(1).innerText = order.productName;
                    row.insertCell(2).innerText = order.quantity;
                    row.insertCell(3).innerText = order.totalPrice;
                    row.insertCell(4).innerText = order.orderDate;
                    row.insertCell(5).innerText = order.status;
                });
            });
    }
});
