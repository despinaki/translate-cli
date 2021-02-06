(defproject translator-cli "0.1.0-SNAPSHOT"
  :description "A CLI to translate words"
  :url "https://github.com/despinaki/translate-cli"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [com.google.cloud/google-cloud-translate "1.95.7"]]
  :main translator-cli.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                      ;  :jvm-opts ["-Dclojure.compiler.direct-linking=true"]
                       }
             :dev {:plugins [[lein-binplus "0.6.6"]]}}
  :bin {:name "translate" ;name of standalone executable
        :bin-path "~/Despoina.Sifouna/bin" ;where executable is placed
        }
  )