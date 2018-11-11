package chapter2.custom

object implicits {
  implicit val strMonoid: Monoid1[String] = new Monoid1[String] {
    override def empty: String = ""
    override def combine(a1: String, a2: String): String = a1 ++ a2
  }

  implicit def sUnionMonoid[A]: Monoid1[Set[A]] = new Monoid1[Set[A]] {
    override def empty: Set[A] = Set.empty[A]
    override def combine(a1: Set[A], a2: Set[A]): Set[A] = a1 union a2
  }

  implicit def mapsMergeMonoid[K, V: Monoid1]: Monoid1[Map[K, V]] = new Monoid1[Map[K, V]] {
    override def empty: Map[K, V] = Map.empty[K, V]
    override def combine(a1: Map[K, V], a2: Map[K, V]): Map[K, V] = ???
  }

  implicit val optionMonoidal: Monoidal[Option] = new Monoidal[Option] {
    def product[A, B](fa: Option[A], fb: Option[B]): Option[(A, B)] = (fa, fb) match {
      case (Some(a), Some(b)) => Some((a, b))
      case _ => None
    }
    def pure[A](a: A): Option[A] = Some(a)
    def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa.map(f)
  }
}
