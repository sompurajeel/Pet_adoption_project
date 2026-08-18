import "./App.css";
import buddyImg from "./assets/buddy.jpg";
import miloImg from "./assets/milo.jpg";
import rockyImg from "./assets/rocky.jpg";

function App() {
  const pets = [
    {
      id: 1,
      name: "Buddy",
      type: "Dog",
      breed: "Golden Retriever",
      age: "2 Years",
      image: buddyImg,
    },
    {
      id: 2,
      name: "Milo",
      type: "Cat",
      breed: "Persian",
      age: "1 Year",
      image: miloImg,
    },
    {
      id: 3,
      name: "Rocky",
      type: "Dog",
      breed: "Labrador",
      age: "3 Years",
      image: rockyImg,
    },
  ];

  return (
    <div className="app">

      {/* NAVBAR */}
      <nav className="navbar">
        <div className="logo">🐾 Pet Adoption</div>

        <div className="nav-links">
          <a href="#home">Home</a>
          <a href="#pets">Pets</a>
          <a href="#about">About</a>
          <a href="#contact">Contact</a>
        </div>
      </nav>

      {/* HERO */}
      <section className="hero" id="home">
        <div className="hero-content">
          <p className="small-title">FIND YOUR NEW BEST FRIEND</p>

          <h1>
            Give a Pet a <span>Forever Home</span>
          </h1>

          <p>
            Every pet deserves love, care and a happy family.
            Find your perfect companion and give them a loving home.
          </p>

          <a href="#pets" className="hero-button">
            Find a Pet ❤️
          </a>
        </div>
      </section>

      {/* PETS */}
      <section className="pets-section" id="pets">
        <div className="section-heading">
          <p>MEET OUR FRIENDS</p>
          <h2>Pets Looking for a Home</h2>
          <span>
            These lovely pets are waiting for someone like you.
          </span>
        </div>

        <div className="pet-container">
          {pets.map((pet) => (
            <div className="pet-card" key={pet.id}>

              <div className="pet-image">
                <img src={pet.image} alt={pet.name} />
              </div>

              <div className="pet-info">
                <h3>{pet.name}</h3>

                <p>
                  <strong>Type:</strong> {pet.type}
                </p>

                <p>
                  <strong>Breed:</strong> {pet.breed}
                </p>

                <p>
                  <strong>Age:</strong> {pet.age}
                </p>

                <button className="adopt-button">
                  Adopt Me ❤️
                </button>
              </div>

            </div>
          ))}
        </div>
      </section>

      {/* ABOUT */}
      <section className="about-section" id="about">
        <div className="about-content">
          <p className="about-title">ABOUT US</p>

          <h2>We Help Pets Find Loving Families</h2>

          <p>
            Our Pet Adoption Platform connects loving people with
            pets looking for a safe and caring home.
          </p>

          <p>
            Browse available pets, learn about them and start your
            adoption journey today.
          </p>
        </div>
      </section>

      {/* CONTACT */}
      <section className="contact-section" id="contact">
        <div className="contact-content">
          <p className="contact-title">GET IN TOUCH</p>

          <h2>Have Questions?</h2>

          <p>
            Contact us to know more about pet adoption.
          </p>

          <a
            href="mailto:petadoption@gmail.com"
            className="email-button"
          >
            ✉ petadoption@gmail.com
          </a>
        </div>
      </section>

      {/* FOOTER */}
      <footer>
        <p>🐾 Pet Adoption Platform</p>
        <p>© 2026 Pet Adoption. All Rights Reserved.</p>
      </footer>

    </div>
  );
}

export default App;