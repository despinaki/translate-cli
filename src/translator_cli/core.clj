(ns translator-cli.core
  (:import [com.google.cloud.translate TranslateOptions Translate$TranslateOption] ;ClassName$NestedClassName
           [java.lang.reflect Array]) 
  (:require [clojure.string])
  (:gen-class))

(defn translate-text
  "Use google cloud translate api to translate a text"
  [target-lan text]
  (let [translate (-> (TranslateOptions/getDefaultInstance) .getService) ;Get service using GOOGLE_APPLICATION_CREDENTIALS environment var
        detection (.getLanguage (.detect translate text))
        options (Array/newInstance Translate$TranslateOption 2)]
    (do
      (aset options 0 (Translate$TranslateOption/sourceLanguage detection))
      (aset options 1 (Translate$TranslateOption/targetLanguage target-lan)))
    ;; (println (vec (.getMethods (class translate))))
    (println (.getTranslatedText (.translate translate text options)))
    ;; (println detection)
    ))

(defn -main
  [& args]
  (let [target-language (first args)
        text (clojure.string/join " " (rest args))]
    (translate-text target-language text)))
;add another condition that checks if lang is in list
;add exception handling