package it.uniroma3.diadia.direzioni;

public enum Direzione {


	nord() {
		@Override
		public Direzione opposta() {
			return sud;
		}
	},

	est() {
		@Override
		public Direzione opposta() {
			return ovest;
		}
	}, 
	sud() {
		@Override
		public Direzione opposta() {
			return nord;
		}
	},
	ovest() {
		@Override
		public Direzione opposta() {
			return est;
		}
	};

	public abstract Direzione opposta();

}
