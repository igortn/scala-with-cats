package chapter2.custom

object functions {

  def sequence[F[_] : Monoidal, A](l: List[F[A]]): F[List[A]] =
    l.foldRight(Monoidal[F].pure(List.empty[A]))(
      (fa: F[A], acc: F[List[A]]) => {
        val prod: F[(A, List[A])] = Monoidal[F].product(fa, acc)
        Monoidal[F].fmap(prod)(pr => pr._1 +: pr._2)
      }
    )

}
