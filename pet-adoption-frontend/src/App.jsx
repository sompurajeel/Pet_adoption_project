import { useState } from "react";
import "./App.css";

function App() {
  const [page, setPage] = useState("home");

  return (
    <div>
      {/* NAVBAR */}
      <nav className="navbar">
        <div className="logo">🐾 Pet Adoption</div>

        <div className="nav-links">
          <button onClick={() => setPage("home")}>Home</button>
          <button onClick={() => setPage("pets")}>Pets</button>
          <button onClick={() => setPage("about")}>About</button>
          <button onClick={() => setPage("login")}>Login</button>
          <button onClick={() => setPage("register")}>Register</button>
        </div>
      </nav>

      {/* HOME PAGE */}
      {page === "home" && (
        <section className="hero">
          <h1>Find Your New Best Friend 🐶🐱</h1>

          <p>
            Give a loving pet a forever home and make a difference.
          </p>

          <button
            className="main-btn"
            onClick={() => setPage("pets")}
          >
            Browse Pets
          </button>
        </section>
      )}

      {/* PETS PAGE */}
      {page === "pets" && (
        <section className="page-section">
          <h2>🐾 Available Pets</h2>

          <div className="pet-grid">

            {/* BUDDY */}
            <div className="pet-card">
              <img
                src="/images/buddy.jpg"
                alt="Buddy"
              />

              <h3>Buddy</h3>

              <p>Friendly Dog</p>

              <button>View Details</button>
            </div>

            {/* MILO */}
            <div className="pet-card">
              <img
                src="/images/milo.jpg"
                alt="Milo"
              />

              <h3>Milo</h3>

              <p>Playful Cat</p>

              <button>View Details</button>
            </div>

            {/* ROCKY */}
            <div className="pet-card">
              <img
                src="/images/rocky.jpg"
                alt="Rocky"
              />

              <h3>Rocky</h3>

              <p>Active Dog</p>

              <button>View Details</button>
            </div>

          </div>
        </section>
      )}

      {/* LOGIN PAGE */}
      {page === "login" && (
        <section className="form-section">

          <h2>Login</h2>

          <input
            type="email"
            placeholder="Enter Email"
          />

          <input
            type="password"
            placeholder="Enter Password"
          />

          <button className="main-btn">
            Login
          </button>

          <p>
            Don't have an account?{" "}
            <span onClick={() => setPage("register")}>
              Register
            </span>
          </p>

        </section>
      )}

      {/* REGISTER PAGE */}
      {page === "register" && (
        <section className="form-section">

          <h2>Create Account</h2>

          <input
            type="text"
            placeholder="Full Name"
          />

          <input
            type="email"
            placeholder="Email"
          />

          <input
            type="text"
            placeholder="Phone Number"
          />

          <input
            type="text"
            placeholder="Address"
          />

          <input
            type="password"
            placeholder="Password"
          />

          <button className="main-btn">
            Register
          </button>

          <p>
            Already have an account?{" "}
            <span onClick={() => setPage("login")}>
              Login
            </span>
          </p>

        </section>
      )}

      {/* ABOUT PAGE */}
      {page === "about" && (
        <section className="page-section about">

          <h2>About Pet Adoption</h2>

          <p>
            Our Pet Adoption Platform connects pet adopters
            with shelters and helps users find loving pets
            for adoption.
          </p>

          <p>
            Users can search pets, view pet details,
            submit adoption requests and access pet-care
            services.
          </p>

        </section>
      )}

      {/* FOOTER */}
      <footer>

        <p>📧 petadoption@gmail.com</p>

        <p>
          © 2026 Pet Adoption Platform
        </p>

      </footer>

    </div>
  );
}

export default App;